package it.musichub.server.rest.impl.routes.health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.musichub.rest.model.ApiError;
import it.musichub.rest.model.DeviceDto;
import it.musichub.rest.model.HealthDto;
import it.musichub.server.rest.impl.AbstractRoute;
import it.musichub.server.rest.impl.RestDtoMapper;
import it.musichub.server.runner.ServiceFactory;
import it.musichub.server.runner.ServiceFactory.ServiceFactoryState;
import it.musichub.server.upnp.model.Device;
import spark.Request;
import spark.Response;

@Api
@Path("/health")
@Produces("application/json")
public class GetHealth extends AbstractRoute {

	@GET
	@ApiOperation(value = "Get health status", nickname = "GetHealth", tags = "health")
	@ApiImplicitParams({ //
//			@ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header"), //
	}) //
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success", response = HealthDto.class), //
			@ApiResponse(code = 500, message = "Error", response = ApiError.class), //
//			@ApiResponse(code = 401, message = "Unauthorized", response = ApiError.class), //
	})
	public Object handle(@ApiParam(hidden = true) Request request, @ApiParam(hidden = true) Response response) throws Exception {
		
		ServiceFactory sf = ServiceFactory.getInstance();
		String version = sf.getVersion();
		String state = sf.getState() != null ? sf.getState().name() : null;
		
		if (state == null || !ServiceFactoryState.started.name().equals(state)){
			response.status(500);
			return new ApiError(response.status(), "Server is in state "+state);
		}
		
		DeviceDto deviceDto = null;
		Device device = getUpnpControllerService().getSelectedDevice();
		if (device != null)
			deviceDto = RestDtoMapper.toDeviceDto(device);
			
		
		return new HealthDto(version, state, deviceDto);
	}
	
}

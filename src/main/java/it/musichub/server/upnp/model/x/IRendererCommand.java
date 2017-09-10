/**
 * Copyright (C) 2013 Aurélien Chabot <aurelien@chabot.fr>
 * 
 * This file is part of DroidUPNP.
 * 
 * DroidUPNP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DroidUPNP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DroidUPNP.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.musichub.server.upnp.model.x;

public interface IRendererCommand {

	// Pause/resume backgroud state update
	public void pause();

	public void resume();

	// / Status
	public void commandPlay();

	public void commandStop();

	public void commandPause();

	public void commandToggle();

	public void updateStatus();

	// / Position
	public void commandSeek(String relativeTimeTarget);

	public void updatePosition();

	// / Volume
	public void setVolume(final int volume);

	public void setMute(final boolean mute);

	public void toggleMute();

	public void updateVolume();

	// / URI
	public void launchItem(final IDIDLItem uri);

	// / Full
	public void updateFull();

}

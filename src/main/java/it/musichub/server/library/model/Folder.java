package it.musichub.server.library.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Folder implements Serializable {

	private File file;
	private String path; //KEY
	private String relativePath;
	private String name;
	private List<Song> songs;
	private Folder father;
	private List<Folder> folders;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public void addSong(Song song) {
		if (songs == null)
			songs = new ArrayList<>();
		
		songs.add(song);
	}

	public Folder getFather() {
		return father;
	}

	public void setFather(Folder father) {
		this.father = father;
	}
	
	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}
	
	public void addFolder(Folder folder) {
		if (folders == null)
			folders = new ArrayList<>();
		
		folders.add(folder);
	}
	
	/**
	 * Recupera una sottocartella se esiste
	 * 
	 * @param folder
	 * @return 
	 */
	public Folder getFolder(Folder folder){
		if (folders != null){
			for (Folder f : folders){
				try {
					if (Files.isSameFile(f.getFile().toPath(), folder.getFile().toPath()))
						return f;
				} catch (IOException e) {
					//Nothing to do
				}
			}
			
		}
		
		return null;
	}
	
	/**
	 * Recupera una sottocartella se esiste
	 * 
	 * @param folder
	 * @return 
	 */
	public Folder getFolderRecursive(Folder folder){
		try {
			if (Files.isSameFile(this.getFile().toPath(), folder.getFile().toPath()))
				return this;
		} catch (IOException e) {
			//Nothing to do
		}

		if (folders != null){
			for (Folder f : folders){

				try {
					if (Files.isSameFile(f.getFile().toPath(), folder.getFile().toPath()))
						return f;
				} catch (IOException e) {
					//Nothing to do
				}
					
				Folder f2 = f.getFolderRecursive(folder);
				if (f2 != null)
					return f2;
			}
			
		}
		
		return null;
	}
	
	/**
	 * Recupera una canzone se esiste
	 * 
	 * @param path Percorso della canzone
	 * @return 
	 */
	public Song getSong(Path filePath){
		if (songs != null){
			for (Song s : songs){
				try {
					if (Files.isSameFile(s.getFile().toPath(), filePath))
						return s;
				} catch (IOException e) {
					//Nothing to do
				}
			}
			
		}
		
		return null;
	}
	
	/**
	 * Recupera una canzone se esiste
	 * 
	 * @param song
	 * @return 
	 */
	public Song getSong(Song song){
		if (songs != null){
			for (Song s : songs){
				try {
					if (Files.isSameFile(s.getFile().toPath(), song.getFile().toPath()))
						return s;
				} catch (IOException e) {
					//Nothing to do
				}
			}
			
		}
		
		return null;
	}
	
	/**
	 * Recupera una canzone se esiste
	 * 
	 * @param song
	 * @return 
	 */
	public Song getSong(String filename){
		if (songs != null){
			for (Song s : songs){
				if (s.getFilename().equals(filename))
					return s;
			}
			
		}
		
		return null;
	}
	
	public String getId(){
		return path != null ? Integer.toString(path.hashCode()) : null; 
	}

	@Override
	public String toString() {
		return "Folder [" + name + " (" + relativePath + ")]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	
}

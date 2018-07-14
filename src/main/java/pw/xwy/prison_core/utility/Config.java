package pw.xwy.prison_core.utility;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
	
	private YamlConfiguration fc = new YamlConfiguration();
	private File file;
	
	public Config(String parent, String name) {
		this(new File(parent), name);
	}
	
	public Config(File folder, String name) {
		folder.mkdirs();
		file = new File(folder, name + ".yml");
		load();
	}
	
	public void load() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			fc.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public Object get(String path) {
		return fc.get(path);
	}
	
	public void set(String path, Object value) {
		fc.set(path, value);
	}
	
	public void saveConfig() {
		try {
			fc.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getStringList(String path) {
		return fc.getStringList(path);
	}
	
	public boolean getBoolean(String path) {
		return fc.getBoolean(path);
	}
	
	public int getInt(String path) {
		return fc.getInt(path);
	}
	
	public void setComment(String path, String... comments) {
		ArrayList<String> strings = new ArrayList<>();
		strings.add("(This config parameter does nothing, it's just here for commenting)");
		strings.addAll(Arrays.asList(comments));
		fc.set(path + ".comment", strings);
	}
	
	public double getDouble(String path) {
		return fc.getDouble(path);
	}
	
	public String getString(String path) {
		return fc.getString(path);
	}
}
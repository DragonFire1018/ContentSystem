package dragonfire.contentsystem.main;

import java.util.Set;
import java.util.UUID;
import de.omel.api.file.*;

public class ContentFile {
	
	private FileBuilder fb;
	
    public ContentFile(UUID uuid) {
		
	   fb = new FileBuilder("plugins//ContentSystem//", uuid.toString() + ".yml");
			
	}
    public void addWorld(String name) {
    	fb.setValue(name + ".world", name);
		
		fb.save();
		
	}
    public Set<String> getWorlds(){
		return fb.getKeys(false);
	}
    public void delWorld(String name) {
		fb.setValue(name, null);
		fb.save();
	}
    public boolean exist(String name) {
		return fb.getString(name) != null;
		
	}
	

}
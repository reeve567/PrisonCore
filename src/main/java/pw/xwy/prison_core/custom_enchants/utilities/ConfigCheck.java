////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.utilities;
// made by reeve
// on 11:26 PM

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigCheck {
	
	JavaPlugin main;
	String license;
	
	public ConfigCheck(JavaPlugin main) {
		this.main = main;
		license = main.getConfig().getString("key");
	}
	
	public boolean Init() {
/*		if (license != null) {

			try {
				Socket socket = new Socket("52.232.190.52",6565);

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				out.println(license);
				if (in.readLine().equals("true")) {
					socket.close();
					return true;
				}
				else {
					socket.close();
					return false;
				}
			}
			catch (IOException e) {
				System.err.println("Setup may have not completed properly!");
			}
		}
		return false;*/
		return true;
	}
	
}

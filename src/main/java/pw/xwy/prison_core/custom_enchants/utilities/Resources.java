////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/6/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.utilities;

import java.util.HashMap;

public class Resources {
	
	HashMap<String, String> strings = new HashMap<>();
	
	public Resources() {
		strings.put("64Diamonds", "&764 Diamonds");
		strings.put("32Diamonds", "&732 Diamonds");
		strings.put("5000", "&a$&25000");
	}
	
	public String getString(String string) {
		return strings.get(string);
	}
	
}

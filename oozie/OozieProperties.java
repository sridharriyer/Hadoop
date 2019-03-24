String oozieProp = System.getProperty("oozie.action.output.properties");
OutputStream os = null;
if (oozieProp != null) {
	File propFile = new File(oozieProp);
	Properties p = new Properties();
	p.setProperty("currentTimeMillis", "" + System.currentTimeMillis() );
        os = new FileOutputStream(propFile);
	p.store(os, "");
	os.close();
} else {
	throw new RuntimeException("oozie.action.output.properties System property not defined");
}

package util;

public class FileUtils {
	public static String getExecutionPath(Class<?> T) {
		String absolutePath = T.getProtectionDomain().getCodeSource().getLocation().getPath();
		absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		absolutePath = absolutePath.replaceAll("%20", " "); // Surely need to do
															// this here
		return absolutePath;
	}
}

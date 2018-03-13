package pers.android.server;

public class NoteServerStart {
	public static void main(String[] args) throws Exception {
		int port = 8888;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				//
			}
		}
		System.out.println("Server Starts");
		new NoteServer().bind(port);
	}
}

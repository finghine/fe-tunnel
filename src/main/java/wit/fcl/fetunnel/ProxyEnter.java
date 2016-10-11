package wit.fcl.fetunnel;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class ProxyEnter {
	public static void main(String[] args) throws Exception {
		Options options = new Options();
		options.addOption("h", "help", false, "Print this usage information");  
		options.addOption("s", "server", false, "start with server mode");  
		options.addOption("c", "client", false, "start with client mode");  
		options.addOption("rp", "remote-port", true, "set remote port" );  
		options.addOption("rh", "remote-host", true, "set remote host" );  
		options.addOption("lp", "local-port", true, "set local port" );  
		CommandLineParser parser = new BasicParser( );  
//		CommandLineParser parser = new PosixParser(); 
		CommandLine commandLine = parser.parse( options, args );  
		if(commandLine.hasOption("c"))
		{
			// �ͻ���
			String remote_port= commandLine.getOptionValue("rp","8082");
			String remote_host= commandLine.getOptionValue("rh","localhost");
			String local_port= commandLine.getOptionValue("lp","8081");
			ClientHexDumpProxy.run(Integer.parseInt(local_port), remote_host, Integer.parseInt(remote_port));
			
		}else if(commandLine.hasOption("s"))
		{
			// �����
			String remote_port= commandLine.getOptionValue("rp","8083");
			String remote_host= commandLine.getOptionValue("rh","localhost");
			String local_port= commandLine.getOptionValue("lp","8082");
			ServerHexDumpProxy.run(Integer.parseInt(local_port), remote_host, Integer.parseInt(remote_port));	
		}
		else
		{
			HelpFormatter f = new HelpFormatter();
			f.printHelp("fetunnel -c -lp 8081 -rh xxx.xxx.xxx.xxx -rp 8082", options );
		}
	}
}

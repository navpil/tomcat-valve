# Restrict Url Valve

Based on an `org.apache.catalina.valves.RemoteAddrValve` but adds an additional property `restrictUrl` 
which allows to restrict certain url in an webapp

## Usage

Build the project and copy the `restrictvalve.jar` into the `$CATALINA_HOME/lib`.

Edit `server.xml` and add the valve:

    <Valve 
        className="io.github.navpil.restrictvalve.RemoteAddrStrictUrlValve" 
        allow="127\.\d+\.\d+\.\d+;\d*|::1;\d*|0:0:0:0:0:0:0:1;\d*|.*;9080"	
        addConnectorPort="true"
        restrictUrl="/admin/restricted"
    />
    
The above valve will allow the `/admin/restricted` and `/admin/restricted/` only from localhost or on port 9080.
 
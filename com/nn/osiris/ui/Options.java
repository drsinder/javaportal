package com.nn.osiris.ui;

import org.w3c.dom.*;
//import java.io.*;
import java.util.*;
import java.awt.*;

class Options
{
	// true if multiple connections are allowed
	boolean	multi_connect;
	// true if we disconnect from socket upon novanet signoff
	boolean	disconnect_at_signoff;
	// true if we don't display novanet signon in window
	boolean	disable_signon_display;
	// true if configuration settings are locked
	boolean	lock_configuration;
	
	int		x_start;
	int		y_start;
	
	float scale2x;

	public Options()
	{
		multi_connect = true;
		disconnect_at_signoff = false;
		disable_signon_display = false;
		lock_configuration = false;
		scale2x = 1;
		x_start = 0;
		y_start = 0;
	}

	public void copyTo(Options x)
	{
		x.multi_connect = this.multi_connect;
		x.disconnect_at_signoff = this.disconnect_at_signoff;
		x.disable_signon_display = this.disable_signon_display;
		x.lock_configuration = this.lock_configuration;
		x.scale2x = this.scale2x;
		x.x_start = this.x_start;
		x.y_start = this.y_start;
	}

	public void toXML(StringBuffer sb)
	{
		Point pnt = PlatoFrame.mainFrame.getLocation();
		
		sb.append("<options multi_connect=\""+PlatoAccess.boolString(multi_connect)+"\"");
		sb.append(" disconnect_at_signoff=\""+PlatoAccess.boolString(disconnect_at_signoff)+"\"");
//		sb.append(" disable_signon_display=\""+JPortal.boolString(disable_signon_display)+"\"");
//		sb.append(" lock_configuration=\""+JPortal.boolString(lock_configuration)+"\"");
		sb.append(" scalex=\""+scale2x+"\"");
		sb.append(" x_start=\""+pnt.x+"\"");
		sb.append(" y_start=\""+pnt.y+"\"");
		sb.append(" />\n");
	}

	public void readProperties(Properties p)
	{
		if	(PlatoConsts.is_debugging)
			System.out.println("options set from properties");
		this.disconnect_at_signoff = PlatoAccess.stringBool(p.getProperty("disconnect_signoff"));
		this.disable_signon_display = PlatoAccess.stringBool(p.getProperty("disable_name"));
	}

	public void readNode(Node options_node)
	{
	NamedNodeMap	nnm = options_node.getAttributes();
	Node	n;

		if	(PlatoConsts.is_debugging)
			System.out.println("options set from XML");
		/*
		n = nnm.getNamedItem("multi_connect");
		if	(null != n)
		{
			this.multi_connect = JPortal.stringBool(n.getNodeValue());
			if	(PortalConsts.is_debugging)
				System.out.println("multi-connect="+this.multi_connect);
		}
		*/
		this.multi_connect = true;
		
		n = nnm.getNamedItem("disconnect_at_signoff");
		if	(null != n)
			this.disconnect_at_signoff = PlatoAccess.stringBool(n.getNodeValue());
		/*
		n = nnm.getNamedItem("disable_signon_display");
		if	(null != n)
			this.disable_signon_display = JPortal.stringBool(n.getNodeValue());
		*/
		
		this.disable_signon_display = false;
		
		/*
		n = nnm.getNamedItem("lock_configuration");
		if	(null != n)
			this.lock_configuration = JPortal.stringBool(n.getNodeValue());
		*/
		
		this.lock_configuration = false;
		
		n = nnm.getNamedItem("scalex");
		if	(null != n)
			this.scale2x = Float.parseFloat(n.getNodeValue());
		
		n = nnm.getNamedItem("x_start");
		if	(null != n) {
			this.x_start = Integer.parseInt(n.getNodeValue());
			PlatoConsts.default_xstart = this.x_start;
		}

		n = nnm.getNamedItem("y_start");
		if	(null != n) {
			this.y_start = Integer.parseInt(n.getNodeValue());
			PlatoConsts.default_ystart = this.y_start;
		}

	}
}
package com.mojix.web.utilities;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PLandaeta on 03/11/15.
 */

public class RestUtils
{
	private static ResponseBuilder createBaseResponse()
	{
		return Response.status( 200 ).header( "content-type", "application/json" );
	}

	public static Response sendBadResponse( String cause )
	{
		Map<String, String> messageMap = new HashMap<String, String>();
		messageMap.put( "message", cause );

		return createBaseResponse().status( 400 ).entity(messageMap).build();
	}
	public static Response sendBadResponseFound( String cause )
	{
	Map<String, String> messageMap = new HashMap<String, String>();
	messageMap.put( "message", cause );

	return createBaseResponse().status( 404 ).entity( messageMap ).build();
	}

	public static Response sendOkResponse( Object object )
	{
		return sendOkResponse( object, true );
	}

	public static Response sendOkResponse( Object object, boolean asMessage )
	{
		if( object instanceof String && asMessage )
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put( "message", (String) object );
			return createBaseResponse().status( 200 ).entity( map ).build();
		}
		return createBaseResponse().status( 200 ).entity( object ).build();
	}

	public static Response sendCreatedResponse( Object object )
	{
		if( object instanceof String )
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put( "message", (String) object );
			return createBaseResponse().status( 201 ).entity( map ).build();
		}

		return createBaseResponse().status( 201 ).entity( object ).build();
	}

	public static Response sendDeleteResponse()
	{
		return Response.noContent().status( 204 ).header( "content-type", "application/json" ).build();
	}

	public static Response sendEmptyResponse()
	{
		return Response.noContent().status( 204 ).header( "content-type", "application/json" ).build();
	}
}
/*
 * Copyright 2012 UnboundID Corp.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.unboundid.scim.wink;

import com.unboundid.scim.sdk.SCIMBackend;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;



/**
 * This class is a JAX-RS resource for the Bulk operation, where XML
 * content type is specified in the URL path.
 */
@Path("Bulk.xml")
public class XMLBulkResource extends AbstractBulkResource
{
  /**
   * Create a new instance of the bulk resource.
   *
   * @param application        The SCIM JAX-RS application associated with this
   *                           resource.
   * @param bulkResourceStats  The resource stats for the bulk operation
   *                           end-point.
   * @param backend            The SCIMBackend to use to process individual
   *                           operations within a bulk operation.
   */
  public XMLBulkResource(final SCIMApplication application,
                         final ResourceStats bulkResourceStats,
                         final SCIMBackend backend)
  {
    super(application, bulkResourceStats, backend);
  }



  /**
   * Implement the POST operation consuming and producing XML format.
   *
   * @param inputStream      The content to be consumed.
   * @param servletContext   The servlet context for the request.
   * @param securityContext  The security context for the request.
   * @param headers          The request headers.
   * @param uriInfo          The URI info for the request.
   *
   * @return  The response to the request.
   */
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response doXmlXmlPost(final InputStream inputStream,
                               @Context final ServletContext servletContext,
                               @Context final SecurityContext securityContext,
                               @Context final HttpHeaders headers,
                               @Context final UriInfo uriInfo)
  {
    final RequestContext requestContext =
        new RequestContext(servletContext, securityContext, headers, uriInfo,
                           MediaType.APPLICATION_XML_TYPE,
                           MediaType.APPLICATION_XML_TYPE);
    return postBulk(requestContext, inputStream);
  }
}

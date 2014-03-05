package gov.nih.tbi.semantic.model;

import gov.nih.tbi.repository.model.hibernate.Dataset;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

/**
 * BRICS vocabulary items
 * 
 * @author Nimesh Patel
 * 
 */
public class DatasetRDF {

  public static final String STRING_DATASET = "Dataset";

  // TODO: Dynamic add Disease?
  public static final String BASE_URI_NS =
      "http://ninds.nih.gov/repository/fitbir/1.0/";
  public static final String URI_NS = BASE_URI_NS + STRING_DATASET + "/";

  // Resources
  public static final Resource RESOURCE_DATASET = resource(STRING_DATASET);

  // Properties
  public static final Property PROPERTY_ID = property("datasetId");
  public static final Property PROPERTY_SJI =
      property("submissionRecordJoinId");
  public static final Property PROPERTY_STUDY = property("studyTitle");
  public static final Property PROPERTY_NAME = property("name");
  public static final Property PROPERTY_PUBLICATION_DATE =
      property("publicationDate");
  public static final Property PROPERTY_SUBMIT_DATE = property("submitDate");
  public static final Property PROPERTY_SUBMITTER_NAME =
      property("submitterName");
  public static final Property PROPERTY_STATUS = property("status");
  public static final Property PROPERTY_DATASET_NAME = property("datasetName");

  public static final Resource resource(String local) {

    return ResourceFactory.createResource(BASE_URI_NS + local);
  }

  public static final Property property(String local) {

    return ResourceFactory.createProperty(URI_NS, local);
  }

  public static String generateName(Dataset dataset) {

    return dataset.getName();
  }

  public static Resource createDatasetResource(Dataset dataset) {

    Resource datasetResource = null;

    try {
      datasetResource =
          ResourceFactory.createResource(URIUtil.encodeQuery(URI_NS
              + generateName(dataset)));
    } catch (URIException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return datasetResource;

  }

  // Delete Me
  // public static Property createDatasetProperty(Dataset dataset)
  // {
  //
  // Property datasetProperty = null;
  //
  // try
  // {
  // datasetProperty = ResourceFactory.createProperty(URIUtil.encodeQuery(URI_NS
  // + dataset.getName()));
  // }
  // catch (URIException e)
  // {
  // // TODO Auto-generated catch block
  // e.printStackTrace();
  // }
  // return datasetProperty;
  // }
}

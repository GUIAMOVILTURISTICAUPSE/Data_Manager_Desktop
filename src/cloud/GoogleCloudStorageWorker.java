package cloud;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.auth.Credentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
//Imports the Google Cloud client library
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Bucket.BlobTargetOption;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GoogleCloudStorageWorker {

	private String bucketName = "guiamovilse_recursos_storage";
	
	public String saveImage(String blobId, byte[] content)
	{
	    // Instantiates a client
		//Credentials credentials;
		//StorageOptions.newBuilder().setCredentials(credentials).build();
	    Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();

	    
	    // The name for the new bucket
	    

	    //
	    // Creates the new bucket
	    //Bucket bucket = storage.create(BucketInfo.of(bucketName));
	    Bucket bucket = storage. get(bucketName);
	    
	    Blob blob = bucket.create(blobId, content, BlobTargetOption.doesNotExist());
	    System.out.println(blob.getMediaLink());
	    System.out.printf("Bucket %s created.%n", bucket.getName());
	    return blob.getMediaLink();
	}
	
	//Tomado de https://stackoverflow.com/questions/25141998/how-to-download-a-file-from-google-cloud-storage-with-java
	public byte[] readImage(String blobId)
	{
		/*Storage storage = StorageOptions.newBuilder()
	            .setProjectId(projectId)
	            .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountJSON)))
	            .build()
	            .getService();
	            */
		Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
		Blob blob = storage.get(bucketName, blobId);
		ReadChannel readChannel = blob.reader();
		FileOutputStream fileOuputStream;
		try {
			fileOuputStream = new FileOutputStream("imagenTest.jpg");
			fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
			fileOuputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob.getContent();
		
	}
	
	public boolean checkIfImageExists(String blobId, String url)
	{
		Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
		Blob blob = storage.get(bucketName, blobId);
		return blob.getSelfLink().equalsIgnoreCase(url);
		
	}
}

package cloud;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.imageio.ImageIO;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.auth.Credentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
//Imports the Google Cloud client library
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Bucket.BlobTargetOption;

import controllers.ControllerHelper;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.google.appengine.api.images.Image;

import com.google.auth.oauth2.*;

public class GoogleCloudStorageWorker {

	private static String bucketName = "guiamovilse_recursos_storage";
	
	public String saveImage(String blobId, byte[] content)
	{
	    // Instantiates a client
		//Credentials credentials;
		//StorageOptions.newBuilder().setCredentials(credentials).build();
		try {
			//GoogleCredentials credential = GoogleCredential.getApplicationDefault();
			Credentials c = GoogleCredentials.getApplicationDefault();
			//GoogleCredential c1 = GoogleCredential.getApplicationDefault();
			StorageOptions.getDefaultInstance();
			Storage storage = (Storage) StorageOptions.newBuilder().setCredentials(c).build().getService();

	    //StorageOptions.Builder().newBuilder().setCredentials(credential).build();
	    // The name for the new bucket
	    

	    //
	    // Creates the new bucket
	    //Bucket bucket = storage.create(BucketInfo.of(bucketName));
	    Bucket bucket = storage. get(bucketName);

	    Blob blob = bucket.create(blobId, content, BlobTargetOption.doesNotExist());
	    System.out.println(blob.getMediaLink());
	    System.out.printf("Bucket %s created.%n", bucket.getName());
	    
	    return blob.getMediaLink();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Tomado de https://stackoverflow.com/questions/25141998/how-to-download-a-file-from-google-cloud-storage-with-java
	public static byte[] readImage(String blobId)
	{
		/*Storage storage = StorageOptions.newBuilder()
	            .setProjectId(projectId)
	            .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountJSON)))
	            .build()
	            .getService();
	            */
		Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
		if(storage==null) return null;
		
		Blob blob = storage.get(bucketName, blobId);
		if(blob==null)
		{
			return null;
		}else {
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
		}
		return blob.getContent();
		
	}
	
	public boolean checkIfImageExists(String blobId, String url)
	{
		Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
		Blob blob = storage.get(bucketName, blobId);
		return blob.getSelfLink().equalsIgnoreCase(url);
	}
	
	/*
	public static javafx.scene.image.Image getImage(String imageId)
	{
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	    BlobKey blobKey = blobstoreService.createGsBlobKey("/gs/" + bucketName + "/image.jpeg");
	    Image blobImage = ImagesServiceFactory.makeImageFromBlob(blobKey);
	    return convertToJavaFXImage(blobImage.getImageData(), blobImage.getWidth(), blobImage.getHeight());
	}*/
	
	public static javafx.scene.image.Image getImage(String imageId)
	{
		javafx.scene.image.Image javaFXImage = null;
		byte[] imageAsByteArray = readImage(imageId);
		if(imageAsByteArray != null)
			javaFXImage = convertToJavaFXImage(readImage(imageId), 300, 300);
		return javaFXImage;
	}
	
	public static javafx.scene.image.Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
		WritableImage image = new WritableImage(width, height);
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(raw);
			BufferedImage read = ImageIO.read(bis);
			image = SwingFXUtils.toFXImage(read, null);
		} catch (IOException ex) {
			ControllerHelper.mostrarAlertaError("No se puede decodificar imagen desde byte array.");
		}
		return image;
	}
	

}

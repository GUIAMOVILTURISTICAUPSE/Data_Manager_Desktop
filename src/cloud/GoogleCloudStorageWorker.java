package cloud;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

//import com.google.auth.Credentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
//Imports the Google Cloud client library
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Bucket.BlobTargetOption;
//The Google cloud Image Library from APP Engine. For magic URL


import controllers.ControllerHelper;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;

import autovalue.shaded.com.google.common.common.collect.Lists;

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
			
			//Funcionaba asi antes... era bueno para desarrollo. En Oct 2018 no funcionaba.
			//Credentials c = GoogleCredentials.getApplicationDefault();
			//GoogleCredential c1 = GoogleCredential.getApplicationDefault();
			
			//Esta forma no funciona por alguna razon
			GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/ivansanchez/Google Drive/OneDrive/UPSE/Investigacion/Proyecto Guia Movil/Google Cloud/New/GuiaMovilSE-be01e9ac19c3.json"))
			        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
			
			//ESta forma funciona, pero no es ideal para produccion
			GoogleCredentials credentials2 = GoogleCredentials.fromStream(new FileInputStream("/Users/ivansanchez/.config/gcloud/application_default_credentials.json"))
			        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
			
			StorageOptions.getDefaultInstance();
			//StorageOptions.newBuilder().setProjectId("practica-20-7e346");
			
			Storage storage = (Storage) StorageOptions.newBuilder().setCredentials(credentials).build().getService();

			//NEW OCT 2018, to get id not from enviroment but from config
			//Storage storage = (Storage) StorageOptions.newBuilder().setProjectId("practica-20-7e346").setCredentials(c).build().getService();

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
		} catch (StorageException se)
		{
			se.printStackTrace();
			ControllerHelper.mostrarAlertaError("Error de Almacenamiento. Se pudo conectar con Google Cloud, pero Storage dio error. Pueden ser permisos o que el identificador de archivo ya existe. Intente probar con otro ID.");
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
		Blob blob = null;
		try {
			blob = storage.get(bucketName, blobId);
			ReadChannel readChannel = blob.reader();
			FileOutputStream fileOuputStream;

			fileOuputStream = new FileOutputStream("imagenTest.jpg");
			fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
			fileOuputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException se)
		{
			se.printStackTrace();
			return null;
		}

		return blob.getContent();

	}

	public boolean checkIfImageExists(String blobId, String url)
	{
		Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
		Blob blob = storage.get(bucketName, blobId);
		return blob.getSelfLink().equalsIgnoreCase(url);
	}

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

package data_layer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.couchbase.client.*;
import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.DeserializationFeature;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.auth.Authenticator;
import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.document.EntityDocument;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.deps.com.fasterxml.jackson.annotation.*;

public class CouchbaseManager<K, V> implements RepositoryManager<K, V>
{
	

*/
    private final Class<V> valueTypeParameterClass;
    final CouchbaseCluster cluster;
    final ObjectMapper mapper = new ObjectMapper();
    final String clusterIP = "186.178.10.221";
    final String cbUser = "Admincb";
    final String cbPass = "B/cb_1*Fab4s3";
    final String port = "";
    final Bucket bucket;


    private final String dataBaseName = "GuiaMovilSE";
    

    //@Inject
    //private Gson gson;

    public CouchbaseManager(final Class<V> valueClass)
    {
    	this.valueTypeParameterClass = valueClass;
        configurarMapper();
        
        //Authenticator auth;
        
        CouchbaseEnvironment cbe = configurarCouchBaseEnvironment();
        
        //cluster.authenticate(auth);
        cluster = CouchbaseCluster.create(cbe, this.clusterIP);
   
        //bucket = cluster.openBucket(cbUser, cbPass);
        bucket = cluster.openBucket(dataBaseName);
        

    }
    
    private CouchbaseEnvironment configurarCouchBaseEnvironment()
    {
    	CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
    		    //this set the IO socket timeout globally, to 45s
    		    .socketConnectTimeout((int) TimeUnit.SECONDS.toMillis(45))
    		    //this sets the connection timeout for openBucket calls globally (unless a particular call provides its own timeout)
    		    .connectTimeout(TimeUnit.SECONDS.toMillis(60))
    		    .build();
    	return env;
    }

    private void configurarMapper()
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Obtiene cualquier documento de la base (si existe) y lo deserializa a su clase
     * adecuada a traves de Genericos.
     * @param key
     * @return
     */
    public V get(K key)
    {
        //Convertir la Llave a String...
        // Por alguna razon el codigo original asume que puede haber otro tipo de llaves
        // pero los metodos oficiales de CouchBase no soportan eso
        String llave = key.toString();

        V res = null;
        if (key != null)
        {
            JsonDocument jd  = bucket.get(llave);

            if (jd != null)
            {
                try {
                    Map<String, Object> map = jd.content().toMap();
                    res = mapper.convertValue(map, valueTypeParameterClass);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    System.err.println("ErrorCouchbase: Error de conversion de Map " +
                            e.getMessage());
                }
            }
        }
        return res;
    }

    /**
     * Persiste cualquier objeto serializable.
     * @param o
     * @param overwrite Verdadero si deseamos sobreescribir el documento
     */
    public void save(Object o, boolean overwrite)throws CouchbaseException
    {
    	Map<String, Object> props = mapper.convertValue(o, Map.class);
        String id = (String) props.get("_id");
    	
    	//String jsonString = mapper.writeValueAsString(o);
    	JsonObject jo = JsonObject.from(props);

    	if(id == null)
        {
    		throw new CouchbaseException("Objeto o clase sin ID definido");
        }else{
        	JsonDocument jd = JsonDocument.create(id, jo);
        	
        	if(overwrite)
        	{
        		JsonDocument createdDoc = bucket.upsert(jd);
        	}else{
        		JsonDocument createdDoc = bucket.insert(jd);
        	}
        }
    }



}


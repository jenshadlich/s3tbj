package de.jeha.s3tbj;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

import java.io.IOException;
import java.util.Locale;

/**
 * @author jenshadlich@googlemail.com
 */
public class CreateBucket {

    public static void main(String... args) throws IOException {
        Locale.setDefault(Locale.ENGLISH);

        final String accessKey = "GMGR882QK9J3346TICDX";
        final String secretKey = "edd7NaBJWhPsVKue3eH89K337aQ6UNdBF83PZDNu";
        final String endpoint = "localhost:8888";
        final String bucket = "bucket-to-create";

        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final ClientConfiguration clientConfiguration = new ClientConfiguration()
                .withProtocol(Protocol.HTTP)
                .withUserAgentPrefix("s3tb")
                .withSignerOverride("S3SignerType"); // V2 signatures

        final AmazonS3 s3Client = new AmazonS3Client(credentials, clientConfiguration);
        s3Client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).disableChunkedEncoding().build());
        s3Client.setEndpoint(endpoint);

        s3Client.createBucket(bucket);
        if (s3Client.doesBucketExist(bucket)) {
            System.out.println("Done successfully.");
        }
    }

}

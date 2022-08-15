package tutorial.learn.akka._6_blockchain_core.utils;

import lombok.extern.slf4j.Slf4j;
import tutorial.learn.akka._6_blockchain_core.Block;
import tutorial.learn.akka._6_blockchain_core.HashResult;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
public class BlockChainUtils {

    public static String calculateHash(String data) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] rawHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer();

            for (byte hash : rawHash) {
                String hex = Integer.toHexString(0xff & hash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            log.debug(e.getLocalizedMessage());
            return null;
        }
    }

    public static HashResult mineBlock(Block block, int difficultyLevel, int startNonce, int endNonce) {
        String hash = new String(new char[difficultyLevel]).replace("\0", "X");
        String target = new String(new char[difficultyLevel]).replace("\0", "0");

        int nonce = startNonce;

        while (!hash.substring(0, difficultyLevel).equals(target) && nonce < endNonce) {
            nonce++;
            String dataToEncode = block.getPreviousHash() + block.getTransaction().getTimestamp() + nonce + block.getTransaction();
            hash = calculateHash(dataToEncode);
        }

        if (hash.substring(0, difficultyLevel).equals(target)) {
            HashResult hashResult = new HashResult();
            hashResult.foundAHash(hash, nonce);
            return hashResult;
        } else {
            return null;
        }
    }

    public static boolean validateBlock(Block block) {
        String dataToEncode = block.getPreviousHash() + block.getTransaction().getTimestamp() + block.getNonce() + block.getTransaction();
        String checkHash = calculateHash(dataToEncode);

        return (Objects.requireNonNull(checkHash).equals(block.getHash()));
    }
}

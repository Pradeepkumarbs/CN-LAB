import java.math.BigInteger;
import java.util.*;

class RSAalgorithm{
        BigInteger prk, puk, mod;

        void getkeys(int bitlen){
                Random r = new Random();
                BigInteger p = BigInteger.probablePrime(bitlen, r);
                BigInteger q = BigInteger.probablePrime(bitlen, r);
                mod = p.multiply(q);
                BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
                puk = BigInteger.probablePrime(bitlen/2, r);
                while(!phi.gcd(puk).equals(BigInteger.ONE) || puk.compareTo(phi) >= 0 ){
                        puk = BigInteger.probablePrime(bitlen/2, r);
                }
                prk = puk.modInverse(phi);
                System.out.println("Public Key: ( e= " + puk + ", \n n = "+ mod + ")");
                System.out.println("\nPrivate Key: ( d= " + prk + ",\n n = "+ mod + ")\n");
        }

        BigInteger encrypt(BigInteger m){
                return m.modPow(puk, mod);
        }

        BigInteger decrypt(BigInteger c){
                return c.modPow(prk, mod);
        }
}

class Lab7RSA{
        public static void main(String[] args){
                RSAalgorithm rsa = new RSAalgorithm();
                rsa.getkeys(128);
                //rsa.getkeys(512);
                
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter the message to be encrypted: ");
                BigInteger m = new BigInteger(sc.next().getBytes());
                BigInteger c = rsa.encrypt(m);
                System.out.println("Encrypted message: " + c.longValue());
                BigInteger d = rsa.decrypt(c);
                System.out.println("Decrypted message: " + new String(d.toByteArray()));
                sc.close();
        }
}
/*OUTPUT         --- for(128) BitLength

Public Key: ( e= 14577279857851413217, 
 n = 56560697246939974558213067129368457105994759103997926538541107275350484856381)

Private Key: ( d= 30467373092056530272176988039454827034152293156686231551415583195052991842053,
 n = 56560697246939974558213067129368457105994759103997926538541107275350484856381)

Enter the message to be encrypted: pradeep
Encrypted message: 6350377780409852683
Decrypted message: pradeep


//output -2  ---for(512) BitLength

Public Key: ( e= 114668990046316604713755445285004527774606546611156567613974017274002914838549, 
 n = 94952984005592419234739765581864384012977066348555531533336872886602489607433732764638465608068329445098595799099251635485655640407760459717215007061746184981388326937523361040860608788329488819307041687927192162326310051312292556721443397360701099647203610553459925338714106518455492689759122533684874713553)

Private Key: ( d= 51153369074398637467990109822892070571798536851683648413985204546557263314901598392365192384586030151322951435900012994404758343152945909648958495831262717481326893654379360941909039034356942076558543553566209497608082689207409187881722157373862844990194331834318110716010951287281073676280198858959990608969,
 n = 94952984005592419234739765581864384012977066348555531533336872886602489607433732764638465608068329445098595799099251635485655640407760459717215007061746184981388326937523361040860608788329488819307041687927192162326310051312292556721443397360701099647203610553459925338714106518455492689759122533684874713553)

Enter the message to be encrypted: jsstu
Encrypted message: 8424428630744838927
Decrypted message: jsstu


*/

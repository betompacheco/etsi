package br.gov.frameworkdemoiselle.policy.engine.asn1.etsi;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;

public class PolicyIssuerName extends ASN1Object {

    private Map<ObjectIdentifier, String> issuerNames;
    private String issuerName;

    @Override
    public void parse(ASN1Primitive primitive) {
        if (primitive instanceof DLSequence) {
            DLSequence sequence = (DLSequence) primitive;
            ASN1Encodable asn1Encodable = sequence.getObjectAt(0);
            if (asn1Encodable instanceof DERTaggedObject) {
                DERTaggedObject derTaggedObject = (DERTaggedObject) asn1Encodable;
                ASN1Primitive object = derTaggedObject.getObject();
                if (object instanceof DEROctetString) {
                    OctetString octetString = new OctetString();
                    octetString.parse(object);
                    this.issuerName = octetString.getValueUTF8();
                } else if (object instanceof DERSequence) {
                    DERSequence sequence2 = (DERSequence) object;
                    for (int i = 0; i < sequence2.size(); i++) {
                        ASN1Encodable obj = sequence2.getObjectAt(i);
                        if (obj instanceof DERSet) {
                            DERSet set = (DERSet) obj;
                            ASN1Encodable object2 = set.getObjectAt(0);
                            if (object2 instanceof DERSequence) {
                                DERSequence sequence3 = (DERSequence) object2;
                                ObjectIdentifier objectIdendifier = new ObjectIdentifier();
                                objectIdendifier.parse(sequence3.getObjectAt(0).toASN1Primitive());
                                String name = null;
                                ASN1Encodable object3 = sequence3.getObjectAt(1);
                                if (object3 instanceof DERPrintableString) {
                                    name = ((DERPrintableString) object3).getString();
                                } else if (object3 instanceof DERUTF8String) {
                                    name = ((DERUTF8String) object3).getString();
                                } else {
                                    System.out.println("Não foi possível reconhecer o objeto da classe [" + object3.getClass() + "]: toString [" + object3.toString() + "]");
                                }
                                if (this.issuerNames == null) {
                                    this.issuerNames = new HashMap<ObjectIdentifier, String>();
                                }
                                this.issuerNames.put(objectIdendifier, name);
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<ObjectIdentifier, String> getIssuerNames() {
        return issuerNames;
    }

    public void setIssuerNames(Map<ObjectIdentifier, String> issuerNames) {
        this.issuerNames = issuerNames;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    @Override
    public String toString() {
        if (this.issuerName != null) {
            return this.issuerName;
        }
        String result = "";
        if (this.issuerNames != null && !this.issuerNames.isEmpty()) {
            for (ObjectIdentifier oid : this.issuerNames.keySet()) {
                result = result + oid.getValue() + "=" + this.issuerNames.get(oid) + ",";
            }
            return result.substring(0, result.length() - 1);
        }
        return null;
    }

}

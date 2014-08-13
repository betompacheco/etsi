package br.gov.frameworkdemoiselle.policy.engine.asn1.icpb;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.esf.OtherHashAlgAndValue;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class PoliciesDigest extends ASN1Object {

    private OtherHashAlgAndValue textualPolicyDigest;
    private OtherHashAlgAndValue asn1PolicyDigest;
    private OtherHashAlgAndValue xmlPolicyDigest;

    enum TAG {

        textualPolicyDigest(0),
        asn1PolicyDigest(1),
        xmlPolicyDigest(2);

        int value;

        private TAG(int value) {
            this.value = value;
        }

        public static TAG getTag(int value) {
            for (TAG tag : TAG.values()) {
                if (tag.value == value) {
                    return tag;
                }
            }
            return null;
        }
    }

    @Override
    public void parse(ASN1Primitive derObject) {
        ASN1Sequence derSequence = ASN1Object.getDERSequence(derObject);
        int total = derSequence.size();
        for (int i = 0; i < total; i++) {
            DERTaggedObject derTaggedObject = (DERTaggedObject)derSequence.getObjectAt(i);
            ASN1Sequence otherHashAlgAndValue = ASN1Object.getDERSequence(derTaggedObject);
            ASN1ObjectIdentifier hashAlgorithm = (ASN1ObjectIdentifier)((ASN1Sequence)otherHashAlgAndValue.getObjectAt(0)).getObjectAt(0);
            AlgorithmIdentifier ai = new AlgorithmIdentifier(hashAlgorithm, null);
            ASN1OctetString hashValue = (ASN1OctetString)otherHashAlgAndValue.getObjectAt(1);
            TAG tag = TAG.getTag(derTaggedObject.getTagNo());
            switch (tag) {
                case asn1PolicyDigest:
                    asn1PolicyDigest = new OtherHashAlgAndValue(ai, hashValue);
                    break;
                case textualPolicyDigest:
                        textualPolicyDigest = new OtherHashAlgAndValue(ai, hashValue);
                    break;
                case xmlPolicyDigest:
                    xmlPolicyDigest = new OtherHashAlgAndValue(ai, hashValue);
                    break;
                default:
                    break;
            }
        }
    }

    public OtherHashAlgAndValue getTextualPolicyDigest() {
        return textualPolicyDigest;
    }
    public void setTextualPolicyDigest(OtherHashAlgAndValue textualPolicyDigest) {
        this.textualPolicyDigest = textualPolicyDigest;
    }
    public OtherHashAlgAndValue getAsn1PolicyDigest() {
        return asn1PolicyDigest;
    }
    public void setAsn1PolicyDigest(OtherHashAlgAndValue asn1PolicyDigest) {
        this.asn1PolicyDigest = asn1PolicyDigest;
    }
    public OtherHashAlgAndValue getXmlPolicyDigest() {
        return xmlPolicyDigest;
    }
    public void setXmlPolicyDigest(OtherHashAlgAndValue xmlPolicyDigest) {
        this.xmlPolicyDigest = xmlPolicyDigest;
    }
    
}

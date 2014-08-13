package br.gov.frameworkdemoiselle.policy.engine.asn1.icpb;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import java.io.UnsupportedEncodingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;


public class PoliciesURI extends ASN1Object {
	
	enum TAG {
		textualPolicyURI(0), asn1PolicyURI(1), xmlPolicyURI(2);
		int value;
		private TAG(int value) { this.value = value; }
		public static TAG getTag(int value) {
			for (TAG tag : TAG.values()) if (tag.value == value) {
                            return tag;
                        } return null;
		}
	}
	
	private String textualPolicyURI;
	private String asn1PolicyURI;
	private String xmlPolicyURI;
        
        
    @Override
    public void parse(ASN1Primitive derObject) {
        ASN1Sequence derSequence = ASN1Object.getDERSequence(derObject);
        int total = derSequence.size();
        for (int i = 0; i < total; i++) {
            DERTaggedObject derTaggedObject = (DERTaggedObject)derSequence.getObjectAt(i);
            DEROctetString policyURI = (DEROctetString)derTaggedObject.getObject();
            PoliciesURI.TAG tag = PoliciesURI.TAG.getTag(derTaggedObject.getTagNo());
            try {
                switch (tag) {
                    case textualPolicyURI:
                    textualPolicyURI = new String(policyURI.getOctets(), "UTF8");
                        break;
                    case asn1PolicyURI:
                            asn1PolicyURI = new String(policyURI.getOctets(), "UTF8");
                        break;
                    case xmlPolicyURI:
                        xmlPolicyURI = new String(policyURI.getOctets(), "UTF8");
                        break;
                    default:
                        break;
                }
            } catch (UnsupportedEncodingException ex) {
            }
        }
    }

    public String getTextualPolicyURI() {
        return textualPolicyURI;
    }

    public void setTextualPolicyURI(String textualPolicyURI) {
        this.textualPolicyURI = textualPolicyURI;
    }

    public String getAsn1PolicyURI() {
        return asn1PolicyURI;
    }

    public void setAsn1PolicyURI(String asn1PolicyURI) {
        this.asn1PolicyURI = asn1PolicyURI;
    }

    public String getXmlPolicyURI() {
        return xmlPolicyURI;
    }

    public void setXmlPolicyURI(String xmlPolicyURI) {
        this.xmlPolicyURI = xmlPolicyURI;
    }
    
    

}

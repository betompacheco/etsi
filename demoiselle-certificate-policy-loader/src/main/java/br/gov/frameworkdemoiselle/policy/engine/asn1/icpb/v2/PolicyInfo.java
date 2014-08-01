package br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import br.gov.frameworkdemoiselle.policy.engine.asn1.GeneralizedTime;
import br.gov.frameworkdemoiselle.policy.engine.asn1.etsi.ObjectIdentifier;
import br.gov.frameworkdemoiselle.policy.engine.asn1.etsi.SigningPeriod;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.esf.OtherHashAlgAndValue;

public class PolicyInfo extends ASN1Object {

    private SigningPeriod signingPeriod;
    private GeneralizedTime revocationDate;
    private ObjectIdentifier policyOID;
    private String policyURI;
    private OtherHashAlgAndValue policyDigest;

    public SigningPeriod getSigningPeriod() {
        return signingPeriod;
    }

    public void setSigningPeriod(SigningPeriod signingPeriod) {
        this.signingPeriod = signingPeriod;
    }

    public GeneralizedTime getRevocationDate() {
        return revocationDate;
    }

    public void setRevocationDate(GeneralizedTime revocationDate) {
        this.revocationDate = revocationDate;
    }

    public ObjectIdentifier getPolicyOID() {
        return policyOID;
    }

    public void setPolicyOID(ObjectIdentifier policyOID) {
        this.policyOID = policyOID;
    }

    public String getPolicyURI() {
        return policyURI;
    }

    public void setPolicyURI(String policyURI) {
        this.policyURI = policyURI;
    }

    public OtherHashAlgAndValue getPolicyDigest() {
        return policyDigest;
    }

    public void setPolicyDigest(OtherHashAlgAndValue policyDigest) {
        this.policyDigest = policyDigest;
    }

    @Override
    public void parse(ASN1Primitive derObject) {
        ASN1Sequence derSequence = ASN1Object.getDERSequence(derObject);
        this.signingPeriod = new SigningPeriod();
        this.signingPeriod.parse(derSequence.getObjectAt(0).toASN1Primitive());
        int indice = 2;
        ASN1Primitive secondObject = derSequence.getObjectAt(1).toASN1Primitive();
        if (secondObject instanceof DERObjectIdentifier) {
            indice = 1;
        }
        if (indice == 2) {
            this.revocationDate = new GeneralizedTime();
            this.revocationDate.parse(secondObject);
        }
        this.policyOID = new ObjectIdentifier();
        this.policyOID.parse(derSequence.getObjectAt(indice).toASN1Primitive());
        DERIA5String policyURI = (DERIA5String) derSequence.getObjectAt(indice + 1);
        this.policyURI = policyURI.getString();
        ASN1Primitive policyDigest = derSequence.getObjectAt(indice + 2).toASN1Primitive();
        ASN1Sequence sequence = ASN1Sequence.getInstance(policyDigest);
//		this.policyDigest = new OtherHashAlgAndValue(sequence);
    }

}

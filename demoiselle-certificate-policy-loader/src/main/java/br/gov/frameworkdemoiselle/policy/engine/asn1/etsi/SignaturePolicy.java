package br.gov.frameworkdemoiselle.policy.engine.asn1.etsi;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;

public class SignaturePolicy {

    private AlgorithmIdentifier signPolicyHashAlg;
    private SignPolicyInfo signPolicyInfo;
    private SignPolicyHash signPolicyHash;

    public AlgorithmIdentifier getSignPolicyHashAlg() {
        return signPolicyHashAlg;
    }

    public void setSignPolicyHashAlg(AlgorithmIdentifier signPolicyHashAlg) {
        this.signPolicyHashAlg = signPolicyHashAlg;
    }

    public SignPolicyInfo getSignPolicyInfo() {
        return signPolicyInfo;
    }

    public void setSignPolicyInfo(SignPolicyInfo signPolicyInfo) {
        this.signPolicyInfo = signPolicyInfo;
    }

    public SignPolicyHash getSignPolicyHash() {
        return signPolicyHash;
    }

    public void setSignPolicyHash(SignPolicyHash signPolicyHash) {
        this.signPolicyHash = signPolicyHash;
    }

    public void parse(ASN1Primitive derObject) {
        ASN1Sequence derSequence = ASN1Object.getDERSequence(derObject);
        this.signPolicyHashAlg = new AlgorithmIdentifier();
        this.signPolicyHashAlg.parse(derSequence.getObjectAt(0).toASN1Primitive());
        this.signPolicyInfo = new SignPolicyInfo();
        this.signPolicyInfo.parse(derSequence.getObjectAt(1).toASN1Primitive());
        if (derSequence.size() == 3) {
            this.signPolicyHash = new SignPolicyHash();
            this.signPolicyHash.parse(derSequence.getObjectAt(2).toASN1Primitive());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Implementar!!!");
        return builder.toString();
    }

}

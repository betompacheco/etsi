package br.gov.frameworkdemoiselle.policy.engine.asn1.etsi;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;

public class AlgorithmIdentifier extends ASN1Object {

    private ObjectIdentifier algorithm;
    private Object parameters;

    public ObjectIdentifier getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(ObjectIdentifier algorithm) {
        this.algorithm = algorithm;
    }

    public Object getParameters() {
        return parameters;
    }

    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    @Override
    public void parse(ASN1Primitive derObject) {
        this.algorithm = new ObjectIdentifier();
        DLSequence derSequence = (DLSequence) derObject;
        this.algorithm.parse(derSequence.getObjectAt(0).toASN1Primitive());
    }

}

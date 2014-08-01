package br.gov.frameworkdemoiselle.policy.engine.asn1.icpb;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DLSequence;

public class LPA extends ASN1Object {

    private Collection<PolicyInfo> policyInfos;
    private Time nextUpdate;

    public Collection<PolicyInfo> getPolicyInfos() {
        return policyInfos;
    }

    public void setPolicyInfos(Collection<PolicyInfo> policyInfos) {
        this.policyInfos = policyInfos;
    }

    public Time getNextUpdate() {
        return nextUpdate;
    }

    public void setNextUpdate(Time nextUpdate) {
        this.nextUpdate = nextUpdate;
    }

    @Override
    public void parse(ASN1Primitive derObject) {
        ASN1Sequence sequence = ASN1Object.getDERSequence(derObject);
        ASN1Primitive policyInfos = sequence.getObjectAt(0).toASN1Primitive();
        DLSequence policyInfosSequence = (DLSequence) policyInfos;
        if (policyInfosSequence != null && policyInfosSequence.size() > 0) {
            this.policyInfos = new ArrayList<PolicyInfo>();
            for (int i = 0; i < policyInfosSequence.size(); i++) {
                PolicyInfo policyInfo = new PolicyInfo();
                policyInfo.parse(policyInfosSequence.getObjectAt(i).toASN1Primitive());
                this.policyInfos.add(policyInfo);
            }
        }
        this.nextUpdate = new Time();
        this.nextUpdate.parse(sequence.getObjectAt(1).toASN1Primitive());
    }

}

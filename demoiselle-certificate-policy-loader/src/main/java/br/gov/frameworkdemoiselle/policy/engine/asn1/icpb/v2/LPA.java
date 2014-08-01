package br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;
import br.gov.frameworkdemoiselle.policy.engine.asn1.GeneralizedTime;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DLSequence;

public class LPA extends ASN1Object {

    private Version version;
    private Collection<PolicyInfo> policyInfos;
    private GeneralizedTime nextUpdate;

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Collection<PolicyInfo> getPolicyInfos() {
        return policyInfos;
    }

    public void setPolicyInfos(Collection<PolicyInfo> policyInfos) {
        this.policyInfos = policyInfos;
    }

    public GeneralizedTime getNextUpdate() {
        return nextUpdate;
    }

    public void setNextUpdate(GeneralizedTime nextUpdate) {
        this.nextUpdate = nextUpdate;
    }

    public void parse(ASN1Primitive derObject) {
        ASN1Sequence sequence = ASN1Object.getDERSequence(derObject);
        ASN1Primitive firstObject = sequence.getObjectAt(0).toASN1Primitive();
        this.version = new Version();
        int indice = 0;
        if (firstObject instanceof ASN1Integer) {
            this.version.parse(firstObject);
            indice++;
        }
        ASN1Primitive policyInfos = sequence.getObjectAt(indice).toASN1Primitive();
        DLSequence policyInfosSequence = (DLSequence) policyInfos;
        if (policyInfosSequence != null && policyInfosSequence.size() > 0) {
            this.policyInfos = new ArrayList<>();
            for (int i = 0; i < policyInfosSequence.size(); i++) {
                PolicyInfo policyInfo = new PolicyInfo();
                policyInfo.parse(policyInfosSequence.getObjectAt(i).toASN1Primitive());
                this.policyInfos.add(policyInfo);
            }
        }
        this.nextUpdate = new GeneralizedTime();
        this.nextUpdate.parse(sequence.getObjectAt(indice + 1).toASN1Primitive());
    }
}

package br.gov.frameworkdemoiselle.policy.engine.asn1;

import br.gov.frameworkdemoiselle.policy.engine.asn1.etsi.SignaturePolicy;
import br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.LPA;
import br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.Time;
import br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.PolicyInfo;
import br.gov.frameworkdemoiselle.policy.engine.factory.PolicyFactory;
import br.gov.frameworkdemoiselle.policy.engine.factory.PolicyFactory.Policy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;

public class Read {

    public static ASN1Primitive readDERFromFile(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException error) {
            throw new RuntimeException(error);
        }
        ASN1InputStream asn1is = new ASN1InputStream(fis);
        ASN1Primitive derObject = null;
        try {
            derObject = asn1is.readObject();
        } catch (IOException error) {
            throw new RuntimeException(error);
        } finally {
            try {
                asn1is.close();
            } catch (IOException error) {
                throw new RuntimeException(error);
            }
        }
        return derObject;
    }

    public static SignaturePolicy readSignaturePolicyFromFile(File file) {
        SignaturePolicy signaturePolicy = new SignaturePolicy();
        ASN1Primitive primitive = Read.readDERFromFile(file);
        signaturePolicy.parse(primitive);
        return signaturePolicy;
    }

    public static LPA readLPAFromFile(File file) {
        LPA listaPoliticaAssinatura = new LPA();
        ASN1Primitive derObject = Read.readDERFromFile(file);
        listaPoliticaAssinatura.parse(derObject);
        return listaPoliticaAssinatura;
    }

    public static br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA readLPAv2FromFile(File file) {
        br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA listaPoliticaAssinaturaV2 = new br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA();
        ASN1Primitive derObject = Read.readDERFromFile(file);
        listaPoliticaAssinaturaV2.parse(derObject);
        return listaPoliticaAssinaturaV2;
    }

    public static void printSignaturePolicyFromFile(File file) {
        SignaturePolicy signaturePolicy = Read.readSignaturePolicyFromFile(file);
        System.out.println(signaturePolicy.toString());
    }

    public static void printLPAFromFile(File file) {
        LPA listaPoliticaAssinatura = Read.readLPAFromFile(file);
        System.out.println("===================================================");
        System.out.println("Próxima Atualização.: " + listaPoliticaAssinatura.getNextUpdate().getTime());
        System.out.println("Qtds Políticas......: " + listaPoliticaAssinatura.getPolicyInfos().size());
        System.out.println("===================================================");
        for (br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.PolicyInfo policyInfo : listaPoliticaAssinatura.getPolicyInfos()) {
            System.out.println("\tPolítica.............: " + policyInfo.getPolicyName());
            System.out.println("\tAplicação............: " + policyInfo.getFieldOfApplication());
            System.out.println("\tPeríodo de Assinatura: " + policyInfo.getSigningPeriod());
            System.out.print("\tStatus...............: ");
            Time revocationDate = policyInfo.getRevocationDate();
            if (revocationDate != null) {
                System.out.println("POLÍTICA REVOGADA");
                System.out.println("\tData de Revogação....: " + (revocationDate != null ? revocationDate.getTime() : "não há data de revogação"));
            } else {
                System.out.println("OK");
            }
            System.out.println("\t===================================================");
        }
    }

    public static void verifySignaturePolicy(String policyFile, String lpaFile) {

    }

    public static void printLPAv2FromFile(File file) {
        br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA listaPoliticaAssinatura = Read.readLPAv2FromFile(file);
        System.out.println("===================================================");
        System.out.println("Próxima Atualização.: " + listaPoliticaAssinatura.getNextUpdate().getDate());
        System.out.println("Qtds Políticas......: " + listaPoliticaAssinatura.getPolicyInfos().size());
        System.out.println("===================================================");
        for (PolicyInfo policyInfo : listaPoliticaAssinatura.getPolicyInfos()) {
            System.out.println("\tPeríodo de Assinatura: " + policyInfo.getSigningPeriod());
            System.out.println("\tOID da Política......: " + policyInfo.getPolicyOID().getValue());
            System.out.println("\tURI da Política......: " + policyInfo.getPolicyURI());
            System.out.println("\tAlgoritmo Hash.......: " + policyInfo.getPolicyDigest().getHashAlgorithm().getAlgorithm().getId());
            System.out.println("\tHash.................: " + policyInfo.getPolicyDigest().getHashValue().toString());
            System.out.print("\tStatus...............: ");
            GeneralizedTime revocationDate = policyInfo.getRevocationDate();
            if (revocationDate != null) {
                System.out.println("POLÍTICA REVOGADA");
                System.out.println("\tData de Revogação....: " + (revocationDate != null ? revocationDate.getDate() : "não há data de revogação"));
            } else {
                System.out.println("OK");
            }
            System.out.println("\t===================================================");
        }
    }

    public static void main(String[] args) {

        PolicyFactory pf = PolicyFactory.getInstance();
        System.out.println(pf.loadPolicy(Policy.AD_RT_CADES_2_1));

        Read.printLPAFromFile(new File("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/LPA.der"));

        Read.printLPAv2FromFile(new File("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/LPAv2.der"));
    }

}

package br.gov.frameworkdemoiselle.policy.engine.asn1;

import br.gov.frameworkdemoiselle.policy.engine.factory.PolicyFactory;
import br.gov.frameworkdemoiselle.policy.engine.factory.PolicyFactory.Policy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Read {

    private static final Logger logger = Logger.getLogger(Read.class.getName());

    public static void main(String[] args) {
        PolicyFactory factory = PolicyFactory.getInstance();

        logger.log(Level.INFO, factory.loadPolicy(Policy.AD_RT_CADES_2_1).toString());

        logger.log(Level.INFO, factory.loadLPA().toString());

        logger.log(Level.INFO, factory.loadLPAv2().toString());

    }

}

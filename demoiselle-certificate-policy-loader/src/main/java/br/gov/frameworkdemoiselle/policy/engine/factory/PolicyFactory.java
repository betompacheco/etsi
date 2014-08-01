/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.policy.engine.factory;

import br.gov.frameworkdemoiselle.policy.engine.asn1.Read;
import br.gov.frameworkdemoiselle.policy.engine.asn1.etsi.SignaturePolicy;
import java.io.File;
import org.bouncycastle.asn1.ASN1Primitive;

/**
 *
 * @author 07721825741
 */
public class PolicyFactory {

    public static final PolicyFactory instance = new PolicyFactory();

    public static final PolicyFactory getInstance() {
        return PolicyFactory.instance;
    }

    public SignaturePolicy loadPolicy(Policy policy) {
        SignaturePolicy signaturePolicy = new SignaturePolicy();
        ASN1Primitive primitive = Read.readDERFromFile(new File(policy.getUrl()));
        signaturePolicy.parse(primitive);
        return signaturePolicy;
    }

    public void loadLPA(LPA lpa) {

    }

    public enum Policy {

        AD_RB_CADES_1_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RB.der"),
        AD_RB_CADES_1_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RB_v1_1.der"),
        AD_RB_CADES_2_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RB_v2_0.der"),
        AD_RB_CADES_2_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RB_v2_1.der"),
        AD_RT_CADES_1_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RT.der"),
        AD_RT_CADES_1_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RT_v1_1.der"),
        AD_RT_CADES_2_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RT_v2_0.der"),
        AD_RT_CADES_2_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RT_v2_1.der"),
        AD_RV_CADES_1_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RV.der"),
        AD_RV_CADES_1_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RV_v1_1.der"),
        AD_RV_CADES_2_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RV_v2_0.der"),
        AD_RV_CADES_2_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RV_v2_1.der"),
        AD_RC_CADES_1_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RC.der"),
        AD_RC_CADES_1_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RC_v1_1.der"),
        AD_RC_CADES_2_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RC_v2_0.der"),
        AD_RC_CADES_2_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RC_v2_1.der"),
        AD_RA_CADES_1_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RA.der"),
        AD_RA_CADES_1_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RA_v1_1.der"),
        AD_RA_CADES_1_2("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RA_v1_2.der"),
        AD_RA_CADES_2_0("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RA_v2_0.der"),
        AD_RA_CADES_2_1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RA_v2_1.der"),
        AD_RA_CADES_2_2("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/PA_AD_RA_v2_2.der");

        private String url;

        private Policy(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public enum LPA {

        lpav1("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/LPA.der"),
        lpav2("/home/07721825741/Documentos/ICP-Brasil/artefatos_assinatura/LPAv2.der");

        private String url;

        private LPA(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
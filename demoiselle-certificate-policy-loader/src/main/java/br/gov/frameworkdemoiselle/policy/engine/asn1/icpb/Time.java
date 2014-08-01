package br.gov.frameworkdemoiselle.policy.engine.asn1.icpb;

import java.text.ParseException;
import java.util.Date;

import org.bouncycastle.asn1.DERGeneralizedTime;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERUTCTime;

import br.gov.frameworkdemoiselle.policy.engine.asn1.ASN1Object;

public class Time extends ASN1Object {
	
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date generalTime) {
		this.time = generalTime;
	}
	
	@Override
	public void parse(ASN1Primitive derObject) {
		if (derObject instanceof DERGeneralizedTime) {
			DERGeneralizedTime derGeneralizedTime = (DERGeneralizedTime)derObject;
			try {
				this.setTime(derGeneralizedTime.getDate());
			} catch (ParseException exception) {
				this.setTime(null);
			}
		} else if (derObject instanceof DERUTCTime) {
			DERUTCTime derUTCTime = (DERUTCTime)derObject;
			try {
				this.setTime(derUTCTime.getDate());
			} catch (ParseException exception) {
				this.setTime(null);
			}
		}
	}

}

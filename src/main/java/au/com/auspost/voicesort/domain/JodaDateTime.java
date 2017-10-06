package au.com.auspost.voicesort.domain;

import au.com.auspost.voicesort.dao.JodaDateTimeUserType;
import org.hibernate.annotations.TypeDef;
import org.joda.time.DateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass // Workaground to get Hibernate to pick it up
@TypeDef(defaultForType = DateTime.class, typeClass = JodaDateTimeUserType.class)

public class JodaDateTime {
}

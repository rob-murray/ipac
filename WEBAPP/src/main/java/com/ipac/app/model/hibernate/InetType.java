
package com.ipac.app.model.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/*
 * Hibernate Type for Postgres INET datatype
 * 
 * @author rmurray
 */
public class InetType implements UserType {
	
	public int[] sqlTypes() {
        return new int[] {
                Types.OTHER
        };
    }

	@Override
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return arg0;
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
		return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable) arg0;
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		return arg0 == arg1;
	}

	@Override
	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet arg0, String[] arg1,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		
	      String grade = arg0.getString(arg1[0]);
	      return arg0.wasNull() ? null : grade;	
	}

	@Override
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2,
			SessionImplementor arg3) throws HibernateException, SQLException {
		
		if (arg1 == null){
			arg0.setNull(arg2, Types.OTHER );
		}else{
			arg0.setObject(arg2, arg1, Types.OTHER );
		}		

	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		return arg0;
	}

	@Override
	public Class returnedClass() {
		return String.class;
	}


}

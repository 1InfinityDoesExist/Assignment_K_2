package com.spring.dependencyInjection.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommitType implements UserType {

	@Override
	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return new int[] { Types.JAVA_OBJECT };
	}

	@Override
	public Class<?> returnedClass() {
		// TODO Auto-generated method stub
		return Commit.class;
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		// TODO Auto-generated method stub
		if (x == null) {
			return y == null;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		final String cellContent = rs.getString(names[0]);
		if (cellContent == null) {
			return null;
		}
		try {

			final ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(cellContent.getBytes("UTF-8"), returnedClass());
		} catch (final Exception ex) {
			throw new RuntimeException("Failed To Convert String:-" + ex.toString());
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub

		if (value == null) {
			st.setNull(index, Types.OTHER);
			return;
		}
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			final StringWriter w = new StringWriter();
			objectMapper.writeValue(w, value);
			w.flush();
			w.close();
			st.setObject(index, w.toString(), Types.OTHER);
		} catch (final Exception ex) {
			throw new RuntimeException("Failed To Convert String:-" + ex.getMessage());
		}

	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(baos);
			oos.flush();
			oos.close();
			baos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			return new ObjectInputStream(bais).readObject();
		} catch (ClassNotFoundException | IOException ex) {
			throw new HibernateException(ex.getMessage());
		}
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		// TODO Auto-generated method stub
		return (Serializable) this.deepCopy(value);
	}

	@Override
	public Object assemble(final Serializable cached, Object owner) throws HibernateException {
		// TODO Auto-generated method stub
		return this.deepCopy(cached);
	}

	@Override
	public Object replace(final Object original, Object target, Object owner) throws HibernateException {
		// TODO Auto-generated method stub
		return this.deepCopy(original);
	}

}

package infrastructure.datasource;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

public class UuidGenerator extends IdentityGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) {
		// UUIDを生成し返却する
		return UUID.randomUUID().toString();
	}
}

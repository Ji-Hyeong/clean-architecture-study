package dev.jh.user.config

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import javax.sql.DataSource

/**
 * Flyway 마이그레이션을 명시적으로 실행하는 설정이다.
 *
 * - 부트 자동 실행이 누락되는 환경을 대비해 애플리케이션 준비 시점에 migrate를 호출한다.
 */
@Configuration
class FlywayConfig(
	private val dataSource: DataSource,
	@Value("\${spring.flyway.locations:classpath:db/migration}")
	private val locations: String,
	@Value("\${spring.flyway.table:flyway_schema_history}")
	private val table: String,
	@Value("\${spring.flyway.validate-on-migrate:false}")
	private val validateOnMigrate: Boolean,
	@Value("\${spring.flyway.baseline-on-migrate:false}")
	private val baselineOnMigrate: Boolean,
	@Value("\${spring.flyway.baseline-version:1}")
	private val baselineVersion: String
) {
	/**
	 * 애플리케이션 준비 시 마이그레이션을 실행한다.
	 */
	@EventListener(ApplicationReadyEvent::class)
	fun migrate() {
		val configuration = Flyway.configure()
			.dataSource(dataSource)
			.locations(locations)
			.table(table)
			.validateOnMigrate(validateOnMigrate)

		if (baselineOnMigrate) {
			configuration.baselineOnMigrate(true)
				.baselineVersion(baselineVersion)
		}

		configuration.load()
			.migrate()
	}
}

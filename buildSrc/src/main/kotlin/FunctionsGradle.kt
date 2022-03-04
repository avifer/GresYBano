import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object FunctionsGradle {

    private const val DIR_PRIVATE_DATA = "privateData"
    private const val FINAL_FILE_PROPERTIES = "SignConfig.properties"

    private const val PATH_FILE = "PATH_FILE"
    private const val STORE_PASSWORD = "STORE_PASSWORD"
    private const val KEY_ALIAS = "KEY_ALIAS"
    private const val KEY_PASSWORD = "KEY_PASSWORD"

    enum class BuildType {
        RELEASE, DEBUG;

        fun getPathFile() =
            getAbsolutePath() + "/" + DIR_PRIVATE_DATA + "/" + name.toLowerCase(Locale.ROOT) + FINAL_FILE_PROPERTIES

    }

    private fun getAbsolutePath() = System.getProperty("user.dir")

    private fun getPropertiesFile(buildType: BuildType): Properties =
        Properties().apply { load(File(buildType.getPathFile()).inputStream()) }

    fun getFileStore(buildType: BuildType): File =
        File(
            getAbsolutePath() + "/" + DIR_PRIVATE_DATA + "/" + getPropertiesFile(buildType).getProperty(
                PATH_FILE
            )
        )

    fun getStorePassword(buildType: BuildType): String =
        getPropertiesFile(buildType).getProperty(STORE_PASSWORD)

    fun getKeyAlias(buildType: BuildType): String =
        getPropertiesFile(buildType).getProperty(KEY_ALIAS)

    fun getKeyPassword(buildType: BuildType): String =
        getPropertiesFile(buildType).getProperty(KEY_PASSWORD)

    fun getVersionCode(): Int =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")).toInt()

}
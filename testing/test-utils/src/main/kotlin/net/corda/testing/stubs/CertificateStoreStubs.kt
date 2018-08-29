package net.corda.testing.stubs

import net.corda.core.internal.div
import net.corda.nodeapi.internal.config.FileBasedCertificateStoreSupplier
import net.corda.nodeapi.internal.config.TwoWaySslOptions
import net.corda.nodeapi.internal.config.TwoWaySslConfiguration
import java.nio.file.Path

class CertificateStoreStubs {

    companion object {

        const val DEFAULT_CERTIFICATES_DIRECTORY_NAME = "certificates"

        @JvmStatic
        fun withStoreAt(certificateStorePath: Path, password: String): FileBasedCertificateStoreSupplier = FileBasedCertificateStoreSupplier(certificateStorePath, password)
    }

    class Signing {

        companion object {

            const val DEFAULT_STORE_FILE_NAME = "nodekeystore.jks"
            const val DEFAULT_STORE_PASSWORD = "cordacadevpass"

            @JvmStatic
            fun withCertificatesDirectory(certificatesDirectory: Path, password: String = DEFAULT_STORE_PASSWORD, certificateStoreFileName: String = DEFAULT_STORE_FILE_NAME): FileBasedCertificateStoreSupplier {

                return FileBasedCertificateStoreSupplier(certificatesDirectory / certificateStoreFileName, password)
            }

            @JvmStatic
            fun withBaseDirectory(baseDirectory: Path, password: String = DEFAULT_STORE_PASSWORD, certificatesDirectoryName: String = DEFAULT_CERTIFICATES_DIRECTORY_NAME, certificateStoreFileName: String = DEFAULT_STORE_FILE_NAME): FileBasedCertificateStoreSupplier {

                return FileBasedCertificateStoreSupplier(baseDirectory / certificatesDirectoryName / certificateStoreFileName, password)
            }
        }
    }

    class P2P {

        companion object {

            @JvmStatic
            fun withCertificatesDirectory(certificatesDirectory: Path, keyStoreFileName: String = KeyStore.DEFAULT_STORE_FILE_NAME, keyStorePassword: String = KeyStore.DEFAULT_STORE_PASSWORD, trustStoreFileName: String = TrustStore.DEFAULT_STORE_FILE_NAME, trustStorePassword: String = TrustStore.DEFAULT_STORE_PASSWORD): TwoWaySslConfiguration {

                val keyStore = FileBasedCertificateStoreSupplier(certificatesDirectory / keyStoreFileName, keyStorePassword)
                val trustStore = FileBasedCertificateStoreSupplier(certificatesDirectory / trustStoreFileName, trustStorePassword)
                return TwoWaySslOptions(keyStore, trustStore)
            }

            @JvmStatic
            fun withBaseDirectory(baseDirectory: Path, certificatesDirectoryName: String = DEFAULT_CERTIFICATES_DIRECTORY_NAME, keyStoreFileName: String = KeyStore.DEFAULT_STORE_FILE_NAME, keyStorePassword: String = KeyStore.DEFAULT_STORE_PASSWORD, trustStoreFileName: String = TrustStore.DEFAULT_STORE_FILE_NAME, trustStorePassword: String = TrustStore.DEFAULT_STORE_PASSWORD): TwoWaySslConfiguration {

                return withCertificatesDirectory(baseDirectory / certificatesDirectoryName, keyStorePassword, trustStorePassword, keyStoreFileName, trustStoreFileName)
            }
        }

        class KeyStore {

            companion object {

                const val DEFAULT_STORE_FILE_NAME = "sslkeystore.jks"
                const val DEFAULT_STORE_PASSWORD = "cordacadevpass"

                @JvmStatic
                fun withCertificatesDirectory(certificatesDirectory: Path, password: String = DEFAULT_STORE_PASSWORD, certificateStoreFileName: String = DEFAULT_STORE_FILE_NAME): FileBasedCertificateStoreSupplier {

                    return FileBasedCertificateStoreSupplier(certificatesDirectory / certificateStoreFileName, password)
                }

                @JvmStatic
                fun withBaseDirectory(baseDirectory: Path, password: String = DEFAULT_STORE_PASSWORD, certificatesDirectoryName: String = DEFAULT_CERTIFICATES_DIRECTORY_NAME, certificateStoreFileName: String = DEFAULT_STORE_FILE_NAME): FileBasedCertificateStoreSupplier {

                    return FileBasedCertificateStoreSupplier(baseDirectory / certificatesDirectoryName / certificateStoreFileName, password)
                }
            }
        }

        class TrustStore {

            companion object {

                const val DEFAULT_STORE_FILE_NAME = "truststore.jks"
                const val DEFAULT_STORE_PASSWORD = "trustpass"

                @JvmStatic
                fun withCertificatesDirectory(certificatesDirectory: Path, password: String = DEFAULT_STORE_PASSWORD, certificateStoreFileName: String = DEFAULT_STORE_FILE_NAME): FileBasedCertificateStoreSupplier {

                    return FileBasedCertificateStoreSupplier(certificatesDirectory / certificateStoreFileName, password)
                }

                @JvmStatic
                fun withBaseDirectory(baseDirectory: Path, password: String = DEFAULT_STORE_PASSWORD, certificatesDirectoryName: String = DEFAULT_CERTIFICATES_DIRECTORY_NAME, certificateStoreFileName: String = DEFAULT_STORE_FILE_NAME): FileBasedCertificateStoreSupplier {

                    return FileBasedCertificateStoreSupplier(baseDirectory / certificatesDirectoryName / certificateStoreFileName, password)
                }
            }
        }
    }
}
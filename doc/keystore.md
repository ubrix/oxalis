# Certificates, keys and key stores in Oxalis

The purpose of this document is to guide you in how to set up your PEPPOL certificates in order to make Oxalis "tick".

## What are certificates used for?

PEPPOL has defined a PKI structure which allows for prudent governance of the access points, the SMP's and so on.

Every low level message passed between access points and between the access point and the SMP, are signed with digital certificates.

The PKI structure comes in two releases:

* V1 which is the current (as of May 2013) scheme. It was initially launched as part of the PEPPOL project a couple of
years ago.
* V2 is the new PKI scheme to be implemented and activated during the summer and autumn of 2013.

The idea was to have a "test" and a "production" hierarchy of certificates. However; in the initial release only
 the test certificates were ever issued.

In V2, there will be a "test" and "production" hierarchy of certificates. The PEPPOL test root certificate are identicial
  for V1 and V2.

![Truststore structure](illustrations/truststore.png)

When your certificate is issued by PEPPOL, it will be signed with the *intermediate* AP certificate.

The long and short of this is: you have 3 trust stores in Oxalis holding the following chain of certificates:

1. V1 test certificates, which are also used in production today.
1. V2 test certificates, having the same "root" CA as the V1 certificates.
1. V2 Production certificates, which has an entirely different "root" CA.

## How are they used in Oxalis?

Oxalis comes with alle of the three trust stores included.

You need only to supply with your key store, holding your private key and the corresponding PEPPOL certificate with your public key embedded.

This key store, which I refer to as the `oxalis-keystore.jks` should be placed in the OXALIS_HOME directory and references in your `oxalis-global.properties

## How do I obtain a PEPPOL certificate for my Access point?

1. Sign a Transport Infrastructure Agreement (TIA) with a PEPPOL authority. Once that is done, you will receive instructions on how
to submit a certificate signing request (CSR).
1. Create your own keystore `oxalis-keystore.jks` holding your private key and your self-signed certificate
1. Send a Certificate Signing Request (CSR) to PEPPOL.
1. You will receive a signed certificate with your public key.
1. Import the signed certificate into the key store (`oxalis-keystore.jks`)
1. Copy the `oxalis-keystore.jks` to your OXALIS_HOME directory.
1. Verify the configuration entry in `oxalis-global.properties`

## How do I create such a keystore?

Sorry, that is outside the scope of this document.

  However; if you have a look at the file `keystore.sh`, which is part of Oxalis, you should get the idea.

  There are many ways to skin a cat; some pepole prefer *openssl* others tools like *portecle*, while others use native tools supplied
  by their operating system.

  The first methods that spring to my mind are:

  * Use Java *keytool* and the *portecle* utility, which may be downloaded from the [Portecle project page at Sourceforge](http://sourceforge.net/projects/portecle/)
  * Use *openssl* togehter with Java *keytool* command
  * Java *keytool* only.
  Import the PEPPOL and intermediate certificates into your keystore, **before** you import the signed certificate returned from PEPPOL.
  * Find some other tool more to your liking

## Creating a keystore using keytool and portecle

  1. Generate the RSA 2048bit keypair:

     ```
     $ keytool -genkey -alias ap-prod -keyalg RSA -keystore oxalis-production-keystore.jks -keysize 2048
     Enter keystore password:
     Re-enter new password:
     What is your first and last name?
       [Unknown]: Donald Duck
     What is the name of your organizational unit?
       [Unknown]:  Ducktown
     What is the name of your organization?
       [Unknown]:  Acme Inc.
     What is the name of your City or Locality?
       [Unknown]:  Oslo
     What is the name of your State or Province?
       [Unknown]:  Akershus
     What is the two-letter country code for this unit?
       [Unknown]:  NO
     Is CN=Donald Duck, OU=Ducktown, O=Acme Inc., L=Oslo, ST=Akershus, C=NO correct?
       [no]:  yes

     Enter key password for <ap-pilot>
             (RETURN if same as keystore password):
     ```
     **When promptet for a key password** - hit enter!

     This will generate your keystore with a single entry holding your private key and self signed certificate with the corresponding public key.
     The alias will be *ap-prod*

  1. Generate the Certificate Signing Request (CSR):

     ```
     keytool -certreq -alias ap-pilot -keystore pilot-keystore.jks -file sendregning-pilot.csr
     ```



## Where can I find more information about the PEPPOL PKI structure?

<to be done>


/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.auth.jsontoken;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.SecurityUtils;
import com.google.api.client.util.StringUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

/**
 * Signs a JSON Web Signature (JWS) using RSA and SHA-256.
 *
 * @since 1.7
 * @author Yaniv Inbar
 * @deprecated (scheduled to be removed in 1.15) Use
 *             {@link com.google.api.client.json.webtoken.JsonWebSignature#signUsingRsaSha256}
 *             instead
 */
@Deprecated
public class RsaSHA256Signer {

  /**
   * Signs a given JWS header and payload based on the given private key.
   *
   * @param privateKey private key
   * @param jsonFactory JSON factory
   * @param header JWS header
   * @param payload JWS payload
   * @return signed JWS string
   * @deprecated (scheduled to be removed in 1.15) Use
   *             {@link com.google.api.client.json.webtoken.JsonWebSignature#signUsingRsaSha256}
   *             instead
   */
  @Deprecated
  public static String sign(PrivateKey privateKey, JsonFactory jsonFactory,
      JsonWebSignature.Header header, JsonWebToken.Payload payload)
      throws GeneralSecurityException, IOException {
    String content = Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(header)) + "."
        + Base64.encodeBase64URLSafeString(jsonFactory.toByteArray(payload));
    byte[] contentBytes = StringUtils.getBytesUtf8(content);
    byte[] signature = SecurityUtils.sign(
        SecurityUtils.getSha256WithRsaSignatureAlgorithm(), privateKey, contentBytes);
    return content + "." + Base64.encodeBase64URLSafeString(signature);
  }

  private RsaSHA256Signer() {
  }
}

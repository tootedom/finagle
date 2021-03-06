package com.twitter.finagle.util

import org.specs.Specification
import java.net.InetSocketAddress

object InetSocketAddressUtilSpec extends Specification {
  "InetSocketAddressUtil" should {
    "parseHosts" in {
      InetSocketAddressUtil.parseHosts("") must beEmpty
      InetSocketAddressUtil.parseHosts(",") must beEmpty
      InetSocketAddressUtil.parseHosts("gobble-d-gook") must throwA[IllegalArgumentException]

      InetSocketAddressUtil.parseHosts("127.0.0.1:11211") mustEqual Seq(new InetSocketAddress("127.0.0.1", 11211))
      InetSocketAddressUtil.parseHosts("127.0.0.1:11211") mustEqual Seq(new InetSocketAddress("127.0.0.1", 11211))
      InetSocketAddressUtil.parseHosts("127.0.0.1:11211,") mustEqual Seq(new InetSocketAddress("127.0.0.1", 11211))
      InetSocketAddressUtil.parseHosts(",127.0.0.1:11211,") mustEqual Seq(new InetSocketAddress("127.0.0.1", 11211))
      InetSocketAddressUtil.parseHosts("127.0.0.1:11211 ") mustEqual Seq(new InetSocketAddress("127.0.0.1", 11211))
      InetSocketAddressUtil.parseHosts(" 127.0.0.1:11211 ") mustEqual Seq(new InetSocketAddress("127.0.0.1", 11211))
      InetSocketAddressUtil.parseHosts("127.0.0.1:11211,127.0.0.1:11212") mustEqual
        Seq(new InetSocketAddress("127.0.0.1", 11211), new InetSocketAddress("127.0.0.1", 11212))
      InetSocketAddressUtil.parseHosts("127.0.0.1:11211 127.0.0.1:11212") mustEqual
        Seq(new InetSocketAddress("127.0.0.1", 11211), new InetSocketAddress("127.0.0.1", 11212))
    }
  }
}
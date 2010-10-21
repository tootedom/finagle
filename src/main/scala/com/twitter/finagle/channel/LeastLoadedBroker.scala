package com.twitter.finagle.channel

import org.jboss.netty.channel.MessageEvent

trait LoadedBroker extends Broker {
  def load: Long
}

class LeastLoadedBroker(endpoints: Seq[LoadedBroker]) extends Broker {
  def dispatch(e: MessageEvent) = leastLoadedEndpoint.dispatch(e)

  private def leastLoadedEndpoint =
    endpoints.min(new Ordering[LoadedBroker] {
    def compare(a: LoadedBroker, b: LoadedBroker) =
      a.load compare b.load
  })
}

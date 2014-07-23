package ch.weisenburger.crosscutting_concerns

import java.util.concurrent.TimeUnit

import com.codahale.metrics.ConsoleReporter

object Registry {
  val metricRegistry = new com.codahale.metrics.MetricRegistry()

  val reporter: ConsoleReporter = ConsoleReporter.forRegistry(metricRegistry)
    .convertRatesTo(TimeUnit.SECONDS)
    .convertDurationsTo(TimeUnit.MILLISECONDS)
    .build();
}
trait Instrumented extends nl.grons.metrics.scala.InstrumentedBuilder {
  val metricRegistry = Registry.metricRegistry
}


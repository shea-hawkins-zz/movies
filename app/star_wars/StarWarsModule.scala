package star_wars

import com.google.inject.AbstractModule
import star_wars.sources.{StarWarsMovieAPISource, StarWarsMovieSource}

class StarWarsModule extends AbstractModule {

  /**
   * This binds the target datasource to the data providing interface
   */
  override def configure() = {
    bind(classOf[StarWarsMovieSource]).to(classOf[StarWarsMovieAPISource])
  }

}

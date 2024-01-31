package codeguru.raytracer.client

import codeguru.raytracer.Raytracer
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.gwt.GwtApplication
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration

class HtmlLauncher : GwtApplication() {
    override fun getConfig(): GwtApplicationConfiguration {
        // Resizable application, uses available space in browser
        return GwtApplicationConfiguration(true)
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    override fun createApplicationListener(): ApplicationListener {
        return Raytracer()
    }
}

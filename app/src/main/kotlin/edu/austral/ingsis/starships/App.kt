package edu.austral.ingsis.starships

import edu.austral.ingsis.starships.ui.*
import edu.austral.ingsis.starships.ui.Collision
import edu.austral.ingsis.starships.ui.ElementColliderType.*
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.stage.Stage
import starships.*

fun main() {
    launch(Starships::class.java)
}

class Starships() : Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val facade = ElementsViewFacade(imageResolver)
    private val keyTracker = KeyTracker()

    companion object {
        val SPACESHIP1 = ImageRef("spaceship1", 70.0, 70.0)
        val SPACESHIP2 = ImageRef("spaceship2", 70.0, 70.0)
        val SPACESHIP3 = ImageRef("spaceship3", 70.0, 70.0)
        val BULLET = ImageRef("bullet", 70.0, 70.0)
        val ASTEROID1 = ImageRef("asteroid", 70.0, 70.0)
        val ASTEROID2 = ImageRef("asteroid", 80.0, 80.0)
        val ASTEROID3 = ImageRef("asteroid", 90.0, 90.0)
        val game = Game()
    }

    override fun start(primaryStage: Stage) {
        game.start()
        val objects = game.objects
        for(gameObject in objects){
            facade.elements[gameObject.id] = ElementModel(gameObject.id, gameObject.position.x, gameObject.position.y, gameObject.size.height, gameObject.size.width, gameObject.rotationDegrees, convertShape(gameObject.shape), convertImage(gameObject))
        }

        facade.timeListenable.addEventListener(TimeListener(facade.elements, game))
        facade.collisionsListenable.addEventListener(CollisionListener(game))
        keyTracker.keyPressedListenable.addEventListener(KeyPressedListener(game))

        val scene = Scene(facade.view)
        keyTracker.scene = scene

        primaryStage.scene = scene
        primaryStage.height = 800.0
        primaryStage.width = 800.0

        facade.start()
        keyTracker.start()
        primaryStage.show()
    }

    override fun stop() {
        facade.stop()
        keyTracker.stop()
    }

    fun convertShape(shape: ObjectShape) : ElementColliderType {
        return when (shape) {
            ObjectShape.CIRCULAR -> Elliptical
            ObjectShape.RECTANGULAR -> Rectangular
            ObjectShape.TRIANGULAR -> Triangular
        }
    }

    fun convertImage(gameObject: GameObject) : ImageRef? {
        return if (gameObject.style == ObjectStyle.SPACESHIP1) SPACESHIP1
        else if(gameObject.style == ObjectStyle.SPACESHIP2) SPACESHIP2
        else if (gameObject.style == ObjectStyle.SPACESHIP3) SPACESHIP3
        else if (gameObject.style == ObjectStyle.ASTEROID1) ASTEROID1
        else if (gameObject.style == ObjectStyle.ASTEROID2) ASTEROID2
        else if (gameObject.style == ObjectStyle.ASTEROID3) ASTEROID3
        else BULLET
    }
}

class TimeListener(private val elements: Map<String, ElementModel>, private val game: Game) : EventListener<TimePassed> {
    override fun handle(event: TimePassed) {
        if (game.isOver) {
            game.showResults()
            game.reset()
        }
        game.update()
        val objects = game.objects
        for (gameObject in objects) {
            val element = elements.get(gameObject.id)
            val values = gameObject.posRotSz
            if (element != null) {
                element.x.set(values[0])
                element.y.set(values[1])
                element.rotationInDegrees.set(values[2])
                element.height.set(values[3])
                element.width.set(values[4])
            }
        }
    }
}

class CollisionListener(private val game: Game) : EventListener<Collision> {
    override fun handle(event: Collision) {
        game.makeCollision(event.element1Id, event.element2Id)
    }

}

class KeyPressedListener(private val game: Game): EventListener<KeyPressed> {
    override fun handle(event: KeyPressed) {
        val map = game.configuration.keyboardConfiguration;
//        if (event.key == KeyCode.S && game.isPaused) game.saveGame()
        when(event.key) {
            map["accelerate-1"] -> game.moveShip(0, true)
            map["stop-1"] -> game.moveShip(0, false)
            map["rotate-left-1"] -> game.rotateShip(0, -5.0)
            map["rotate-right-1"] -> game.rotateShip(0, 5.0)
            map["shoot-1"] -> game.shoot(0)
//            KeyCode.P -> game.pauseOrResumeGame()
            else -> {}
        }
        if (game.players.size == 2){
            when(event.key) {
                map["accelerate-2"] -> game.moveShip(1, true)
                map["stop-2"] -> game.moveShip(1, false)
                map["rotate-left-2"] -> game.rotateShip(1, -5.0)
                map["rotate-right-2"] -> game.rotateShip(1, 5.0)
                map["shoot-2"] -> game.shoot(1)
                else -> {}
            }
        }
    }

}
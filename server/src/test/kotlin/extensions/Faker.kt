package extensions

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.RandomService
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun RandomService.image(width: Int = 100, height: Int = 100): File {
    val faker = Faker()
    val fileName = nextUUID()
    val fakeText = faker.lorem.words()
    val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val graphics: Graphics2D = bufferedImage.createGraphics()

    graphics.color = Color.WHITE
    graphics.fillRect(0, 0, width, height)

    graphics.color = Color.BLACK
    graphics.font = Font("Arial", Font.PLAIN, 20)
    val fontMetrics: FontMetrics = graphics.fontMetrics
    val x = (width - fontMetrics.stringWidth(fakeText)) / 2
    val y = (height - fontMetrics.height) / 2 + fontMetrics.ascent
    graphics.drawString(fakeText, x, y)

    graphics.dispose()

    val outputFile = File.createTempFile(fileName, null)
    ImageIO.write(bufferedImage, "png", outputFile)
    return outputFile
}
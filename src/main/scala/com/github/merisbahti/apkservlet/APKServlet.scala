package com.github.merisbahti.apkservlet

import org.scalatra._
import scalate.ScalateSupport
import scala.xml.XML

class APKServlet extends ApkapiStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/update") {
    val xml = XML.loadFile("./xml")
    <html>
      <body>
        <h1>Updating</h1>
        { xml \\ "artikel" }
      </body>
    </html>
  }

}

package com.github.merisbahti.apkservlet

import org.scalatra._
import scalate.ScalateSupport

class APKServlet extends ApkapiStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}

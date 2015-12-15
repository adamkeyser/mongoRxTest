import ratpack.exec.Promise
import ratpack.groovy.template.MarkupTemplateModule


import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
  bindings {
    module MarkupTemplateModule
    bind MongoService
  }

  handlers {
    get {
        registry.get(MongoService).find().toList().subscribe() {
          render json(it)
        }
    }

    path("works") {
      byMethod {
        get {
          Promise.of { f ->
            registry.get(MongoService).find().toList().subscribe() {
              render f.success(json(it))
            }
          }.then {
            render it
          }
        }
      }
    }

    files { dir "public" }
  }
}

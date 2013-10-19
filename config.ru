require './index'
run Sinatra::Application

config.generators do |g|
      g.template_engine :haml
end

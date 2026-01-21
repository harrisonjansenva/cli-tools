use clap::Parser;
use std::fs;
use std::path::PathBuf;

#[derive(Parser)]
#[command(about, version, about, long_about = None)]

struct Args {
    #[arg(default_value = ".")]
    path: PathBuf,

    #[arg(short, long, default_value = False)]
    all: bool,

    #[arg(short, long, default_value = False)]
    sort: bool,

}

fn main() {
    let args = Args::parse()

    
}

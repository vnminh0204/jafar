module Main where
import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0) regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA (DirAddr 1) 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA (DirAddr 2) 
    , EndProg 
    ]
main = runWithDebugger (debuggerSimplePrint myShow) [prog]

